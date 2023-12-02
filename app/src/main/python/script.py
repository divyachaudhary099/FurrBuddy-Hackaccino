import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier

# Load the dataset
data = pd.read_csv('dataset.csv')
print(data) # Convert categorical variables to one-hot encoding
X = pd.get_dummies(data.drop(columns=['Emotion']), drop_first=True)
y = data['Emotion']

# Split the data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Initialize and train the model
model = RandomForestClassifier()
model.fit(X_train, y_train)
print(model) 

# Function to predict emotion based on user input
def predict_emotion(input_data):
    # Preprocess the input data to match the model's requirements
    # Convert the input_data dictionary into a DataFrame
    input_df = pd.DataFrame([input_data])
    
    # Convert categorical variables to one-hot encoding
    input_df = pd.get_dummies(input_df, drop_first=True)
    
    # Get missing columns in the input dataframe
    missing_cols = set(X.columns) - set(input_df.columns)
    
    # Add missing columns with default value equal to 0
    for col in missing_cols:
        input_df[col] = 0
    
    # Reorder columns to match training data
    input_df = input_df[X.columns]
    
    # Make prediction
    predicted_emotion = model.predict(input_df)
    
    return predicted_emotion

# Create a dictionary representing the input data
input_data = {
    'Animal_Dog': 1, 'Animal_Cat': 0, 'Animal_Fish': 0, 'Animal_Hamster': 0, 'Animal_Rabbit': 0,
    'Species_Labrador Retriever': 1, 'Mouth Open_Yes': 1, 'Eyes_Calm': 1, 'Sound_Panting': 1,
    'Body Language_Sitting': 1
}

# Call the predict_emotion function with the input data
predicted_emotion = predict_emotion(input_data)

# Print the predicted emotion
print("The predicted emotion of the pet is:", predicted_emotion[0])

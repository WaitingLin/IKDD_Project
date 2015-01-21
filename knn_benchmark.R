# makes the KNN submission
#set workspace
setwd("~/R_workspace")

#read train and test file 
train <- read.csv("~/R_workspace/train.csv")
View(train)
test <- read.csv("~/R_workspace/test.csv")
View(test)

install.packages('FNN')
library(FNN)

train <- read.csv("~/R_workspace/train.csv", header=TRUE)
test <- read.csv("~/R_workspace/test.csv", header=TRUE)

labels <- train[,1]
train <- train[,-1]

results <- (0:9)[knn(train, test, labels, k = 3, algorithm="cover_tree")]

write(results, file="knn_benchmark_3.csv", ncolumns=1) 

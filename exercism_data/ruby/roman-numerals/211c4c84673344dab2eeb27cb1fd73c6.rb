#we create a method that depends from the main class Integer so it can be aplied as the test is asking
class Integer
#Here is created an array of arrays containing the values of romans numbers
#The array is defined and sorted in a "specific" way so later can be used by the method 
  @@romanlist = [["M", 1000],
                 ["CM", 900],
                 ["D",  500],
                 ["CD", 400],
                 ["C",  100],
                 ["XC",  90],
                 ["L",   50],
                 ["XL",  40],
                 ["X",   10],
                 ["IX",   9],
                 ["V",    5],
                 ["IV",   4],
                 ["I",    1]]

  def to_roman
#We define the variable remains as the number to whom the method is aplied.
    remains = self
#The roman variable is defined as an empty string so later we can push the roman numbers of the initial array
    roman = ""
#Iterating trhough each roman/integer array
    for sym, num in @@romanlist
#Checking that we are using the right roman number
      while remains >= num
#While our number is bigger we are resting the value of the roman number checked.
        remains -= num
#Then the roman symbol in the array is pushed to the roman string defined before
        roman << sym
      end
    end
#Returning the value
  roman
  end
end

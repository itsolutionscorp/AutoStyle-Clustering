class Year
   # class method
   def Year.leap? (numericYear)
    return isDivisibleByFour(numericYear) && (!isDivisibleByOneHundred(numericYear) || isDivisibleByFourHundred(numericYear))
   end

   def Year.isDivisibleByFour(numericYear) 
    return numericYear % 4 == 0
   end

   def Year.isDivisibleByOneHundred(numericYear) 
    return numericYear % 100 == 0
   end

   def Year.isDivisibleByFourHundred(numericYear) 
    return numericYear % 400 == 0
   end
 end

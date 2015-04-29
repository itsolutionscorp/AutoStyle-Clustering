class Year  
  def Year.leap? number
    number % 400 == 0 || number % 4 == 0 && number % 100 != 0
  end
end

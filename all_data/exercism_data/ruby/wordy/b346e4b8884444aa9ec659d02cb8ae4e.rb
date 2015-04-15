class WordProblem

  def initialize(phrase)
    @phrase = phrase
  end

  def find_math_verbs
    raise ArgumentError if too_complicated?
      
    operands = []
    strings_array = @phrase.split(" ")
    strings_array.each do |string|
      if string == "plus"
        operands << "+"
      end
      if string == "minus"
        operands << "-"
      end
      if string == "multiplied"
        operands << "*"
      end
      if string == "divided"
        operands << "/"
      end
    end
    operands
  end

  def too_complicated?
    !@phrase.include?("plus") && !@phrase.include?("minus") && !@phrase.include?("multiplied") && !@phrase.include?("divided")
  end

  def find_numbers
    numbers_array = @phrase.scan(/\-?[0-9]+/)[0,6]
  end

  def answer
    eval(find_numbers.zip(find_math_verbs).flatten.join)
  end

end

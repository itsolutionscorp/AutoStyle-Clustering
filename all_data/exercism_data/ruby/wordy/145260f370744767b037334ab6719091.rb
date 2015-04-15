class WordProblem

  def initialize(phrase)
    @phrase = phrase
  end

  def answer
    @final_array = find_numbers.zip(find_math_verbs).flatten.compact
    if @final_array.length == 3
      one_operand
    else
      two_operands
    end
  end
  
  private

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
    @phrase.scan(/\-?[0-9]+/)[0,6]
  end

  def one_operand
    eval(@final_array[0..2].join)
  end

  def two_operands
    eval(@final_array[3..4].unshift(one_operand).join)
  end

end

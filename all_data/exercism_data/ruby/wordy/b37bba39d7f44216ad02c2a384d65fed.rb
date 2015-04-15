class WordProblem
  def initialize(sentence)
    @sentence = sentence
  end

  def answer
    parsed = parse(@sentence)
    solve(parsed)
  end

  def parse(sentence)
    too_difficult?(sentence)
    numbers = sentence.scan(/-?\d+/).map { |num| num.to_i}
    operators = sentence.scan(/plus|minus|multiplied by|divided by/)
    parsed = numbers.zip(operators).flatten.compact
    return parsed
  end

  def solve(parsed)
    solution = parsed[0]
    parsed.each_with_index do |element, index|
      if element.is_a?(Fixnum) && !element[index + 1].nil?
        if    parsed[index - 1] == "plus"
          solution += element
        elsif parsed[index - 1] == "minus"
          solution -= element
        elsif parsed[index - 1] == "multiplied by"
          solution *= element
        elsif parsed[index - 1] == "divided by"
          solution /= element
        end
      end
    end
    return solution
  end

  def too_difficult?(sentence)
    raise ArgumentError unless sentence.gsub(/What is|plus|minus|multiplied by|divided by|-?\d+|by|\ *\?*/, "").empty?
  end

end

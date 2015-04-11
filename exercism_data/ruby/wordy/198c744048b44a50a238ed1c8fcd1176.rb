class WordProblem
  OPERATORS = { 'plus' => :+,
                'minus' => :-,
                'multiplied by' => :*,
                'divided by' => :/ }
  GRAMMAR   = /(-*[0-9]+)\s(\w+|\w+\s\w+)\s(-*[0-9]+)
               (\s(\w+|\w+\s\w+)\s(-*[0-9]+))*/x

  attr_reader :str

  def initialize str
    @str = str
  end

  def answer
    begin
      matches = str.match(GRAMMAR)[1..-1]
    rescue
      raise ArgumentError
    end
    nums = matches.values_at(0, 2, 5).map(&:to_i)
    operators = matches.values_at 1, 4
    r1 = nums[0].send OPERATORS[operators[0]], nums[1]
    r2 = r1.send OPERATORS[operators[1]], nums[2] if operators[1]
    r2.nil? ? r1 : r2
  end
end

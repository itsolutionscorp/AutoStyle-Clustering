class WordProblem

  def initialize(word_problem)
    @word_problem = word_problem
  end

  def answer
    array = @word_problem.delete("by").split(/  | /)[2..-1]
    array = array.map do |elem|
      if elem.to_i != 0
        elem.to_i
      elsif elem == "plus"
        elem = "+"
      elsif elem == "minus"
        elem = "-"
      elsif elem == "multiplied"
        elem = "*"
      elsif elem == "divided"
        elem = "/"
      else
        raise ArgumentError, "Nope"
      end
    end
    total(array)
  end

  def total(array)
    total = array[0].method(array[1]).(array[2])
    if array.length > 3
      total.method(array[3]).(array[-1])
    else
      total
    end
  end

end

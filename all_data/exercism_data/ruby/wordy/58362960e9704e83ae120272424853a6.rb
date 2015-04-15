class WordProblem
  def initialize(question)
    @question = question
    @regex = /([\D]+) (-?\d+)/

    raise ArgumentError if @question.scan(@regex).size < 2
  end

  def answer
    partial = 0
    @question.scan(@regex).each{|match| partial = resolve(partial, match)}
    partial
  end

  private

  def resolve(partial, match_array)
    case match_array.first.strip
    when "What is","plus" then partial + match_array[1].to_i
    when "minus" then partial - match_array[1].to_i
    when "multiplied by" then partial * match_array[1].to_i
    when "divided by" then partial / match_array[1].to_i
    end
  end

end

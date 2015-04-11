class WordProblem
  OPS = { "plus" => "+",
          "minus" => "-",
          "divided by" => "/",
          "multiplied by" => "*" }

  def initialize(s)
    @s = s.gsub("What is ", "").chomp("?")
    OPS.each { |k, v| @s.gsub!(k, v) }
    raise ArgumentError unless @s =~ /^[\d+\-*\/ ]+$/
  end

  def answer
    res = 0
    op = nil
    @s.split.each do |t|
      if int?(t)
        i = Integer(t)
        if op.nil?
          res = i
        else
          res = res.method(op.to_sym).call(i)
        end
      elsif op?(t)
        op = t
      end
    end
    res
  end

  private
  def int?(s)
    s =~ /^-?\d+$/
  end

  def op?(s)
    OPS.values.include?(s)
  end
end


question = 'What is -3 plus 7 multiplied by -2?'
p WordProblem.new(question).answer

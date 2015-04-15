class WordProblem

  def initialize(args)
    exp = /What is (?<operand_1>[-\d]+)(?<operation_1>[a-z\s]+)(?<operand_2>[-\d]+)((?<operation_2>[a-z\s]+)(?<operand_3>[-\d]+))?\?/
    @match = exp.match(args)
    raise ArgumentError unless @match
  end

  def answer
    ans = evaluate(@match[:operation_1].strip,@match[:operand_1].to_i, @match[:operand_2].to_i)
    ans = evaluate(@match[:operation_2].strip,ans,@match[:operand_3].to_i) if @match[:operation_2]
    ans
  end
  
  private
  def evaluate(operation, operand_1, operand_2)
    case operation
    when "plus"
      return operand_1 + operand_2
    when "minus"
      return operand_1 - operand_2
    when "multiplied by"
      return operand_1 * operand_2
    when "divided by"
      return operand_1 / operand_2
    else
      raise ArgumentError
    end    
  end  

end

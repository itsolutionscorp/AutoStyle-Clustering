class WordProblem
  SPLITS = {'plus' => '+', 'minus' => '-', 'multiplied by' => '*', 'divided by' => '/'}

  def initialize(phrase)
    @phrase = phrase.gsub(/(plus|minus|multiplied by|divided by)/, SPLITS)
  end

  def answer
    eqn = sanitize(@phrase)

    begin
      eval(eqn)
    rescue Exception => exc
      raise ArgumentError
    end   
  end

  private

  def sanitize(input)
    input.delete("What is ?")
  end

end

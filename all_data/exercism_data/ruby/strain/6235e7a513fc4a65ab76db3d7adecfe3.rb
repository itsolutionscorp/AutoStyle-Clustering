class Array

  def keep(&block)
    finder(:retain, &block)
  end

  def discard(&block)
    finder(:discard, &block)
  end

  private

  def finder(task, &block)
    eval("self.map do |element|
      #{task == :retain ? "if" : "unless"} yield element
        element
      end
    end.compact")
  end

end

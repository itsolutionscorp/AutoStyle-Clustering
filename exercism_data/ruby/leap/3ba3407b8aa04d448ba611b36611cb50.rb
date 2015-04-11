class Year
  def self.leap?(i)
    @ano = i
    if @ano % 4 == 0 || @ano % 400 == 0
      self.answer
    else 
      false
    end
  end

  def self.answer
    if @ano % 100 == 0 && @ano % 400 == 0
        true
      elsif @ano % 100 == 0
        false
      else
        true
    end
  end
end

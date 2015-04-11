class Robot

  def name
    letters = Array.new(2){[*'A'..'Z'].sample}.join
    numbers = Array.new(3){[*'0'..'9'].sample}.join
    @name ||= letters + numbers
  end

  def reset
    @name = nil
  end
end

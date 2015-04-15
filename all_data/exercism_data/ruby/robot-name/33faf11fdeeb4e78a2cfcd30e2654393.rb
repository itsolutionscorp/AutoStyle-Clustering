class Robot
  LETTERS=('A'..'Z').to_a

  def name
    @name ||= LETTERS.shuffle.sample(2).concat((0..9).to_a.shuffle.sample(3)).join
  end

  def reset
    @name = nil
  end
end

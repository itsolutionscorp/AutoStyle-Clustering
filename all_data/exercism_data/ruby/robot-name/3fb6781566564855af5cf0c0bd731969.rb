class Robot
  ALPHA = ('a'..'z').to_a + ('A'..'Z').to_a
  NUM   = (0..9).to_a

  def name
    @name ||= (ALPHA.shuffle.take(2) + NUM.shuffle.take(3)).join
  end

  def reset
    remove_instance_variable(:@name)
    name
  end
end

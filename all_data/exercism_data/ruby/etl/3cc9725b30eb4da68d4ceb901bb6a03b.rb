class ETL

  def self.transform(old_scoreboard)
    old_scoreboard.each_with_object({}) do | (point, char_array), new_scoreboard |
      char_array.each{ |char| new_scoreboard[char.downcase] = point }
    end
  end

end

class ETL
  def self.transform(old_scoreboard)
    old_scoreboard.each_with_object({}) do |(score, letters), new_scoreboard|
      letters.each { |letter| new_scoreboard[letter.downcase] = score }
    end
  end
end

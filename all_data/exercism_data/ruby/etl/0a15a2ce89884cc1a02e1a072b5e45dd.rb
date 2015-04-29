class ETL
  class << self
    def transform(old_scoreboard)
      old_scoreboard.each_with_object({}) do |(score, letters), scoreboard|
        letters.each { |letter| scoreboard[letter.downcase] = score }
      end
    end
  end
end

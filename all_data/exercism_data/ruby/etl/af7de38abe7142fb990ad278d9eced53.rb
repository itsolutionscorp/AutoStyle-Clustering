module ETL
  def self.transform(legacy)
    legacy.each_with_object({}) do |(score,letters),shiny|
      letters.each do |letter|
        shiny[letter.downcase] = score
      end
    end
  end
end

class ETL
  def self.transform old
    old.each_with_object({}) do |(score,letters), out|
      letters.each do |letter|
        out[letter.downcase] = score
      end
    end
  end
end

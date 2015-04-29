class ETL
  class << self
    def transform(old)
      old.each_with_object({}) do |(score, letters), h|
        letters.each { |letter| h[letter.downcase] = score }
      end
    end
  end
end

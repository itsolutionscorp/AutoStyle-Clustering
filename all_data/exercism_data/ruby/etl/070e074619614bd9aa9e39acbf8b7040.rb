class ETL
  class << self
    def transform(old)
      old.reduce({}) do |memo, (score, letters)|
        letters.each do |letter|
          memo[letter.downcase] = score
        end

        memo
      end
    end
  end
end

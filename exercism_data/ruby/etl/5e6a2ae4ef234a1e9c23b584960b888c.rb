class ETL
  def self.transform(old)
    old.reduce({}) do |new, (score, letters)|
      letters.each.with_object(new) do |letter, new|
        new[letter.downcase] = score
      end
    end
  end
end

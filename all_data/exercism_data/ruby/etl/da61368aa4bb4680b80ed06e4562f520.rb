class ETL

  def self.transform(hash)
    new_format = {}
    hash.values.each do |array| 
      array.each do |letter|
        SCORES.values.each do |value|
          new_format[letter.downcase] = SCORES.key(value) if value.match(/#{letter.downcase}/)
        end
      end
    end
    new_format
  end

  private
  def self.scores
    {
      1 => "aeioulnrst",
      2 => "dg",
      3 => "bcmp",
      4 => "fhvwy",
      5 => "k",
      8 => "jx",
      10 => "qz"
    }
  end

  SCORES = self.scores

end

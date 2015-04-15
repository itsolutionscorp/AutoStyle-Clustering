class ETL
  def self.transform(input)
    output = {}
    input.each_pair do | score, letters |
      letters.each {|letter| output[letter.downcase] = score }
    end
    output
  end
end

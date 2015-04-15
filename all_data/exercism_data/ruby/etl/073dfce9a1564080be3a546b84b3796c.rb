class ETL

  def self.transform legacy_data
    new( legacy_data ).transform
  end

  attr_reader :data

  def initialize data
    @data = data
  end

  def transform
    data.each_with_object({}) do |(score, letters), result|
      letters.each { |letter| result[letter.downcase] = score }
    end
  end

end

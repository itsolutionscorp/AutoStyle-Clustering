class ETL

  def self.transform original_data
    new( original_data ).transform
  end

  attr_reader :data

  def initialize data
    @data = data
  end

  def transform
    data.each_with_object Hash.new do |(score, letters), result|
      letters.each { |letter| result[ letter.downcase ] = score }
    end
  end

end

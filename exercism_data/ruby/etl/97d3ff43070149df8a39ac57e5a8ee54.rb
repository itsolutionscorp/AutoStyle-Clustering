class ETL

  def self.transform legacy_data
    new( legacy_data ).transform
  end

  attr_reader :legacy_data, :scores

  def initialize legacy_data
    @legacy_data = legacy_data
    @scores      = Hash.new
  end

  def transform
    legacy_data.each_with_object({}) do |(point, letters), hash|
      letters.each { |letter| hash[letter.downcase] = point }
    end
  end

end

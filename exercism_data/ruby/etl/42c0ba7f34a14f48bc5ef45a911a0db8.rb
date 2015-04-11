# class Etl
class ETL
  def self.transform(old)
    expected = {}
    old.each do |point, char_array|
      char_array.each { |char|  expected[char.downcase] = point }
    end
    expected
  end
end

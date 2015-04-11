# class Etl
class ETL
  def self.transform(old)
    expected = {}
    old.each do |k, a|
      a.each { |e|  expected[e.downcase] = k }
    end
    expected
  end
end

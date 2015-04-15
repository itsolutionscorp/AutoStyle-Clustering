class ETL

  def self.transform old
    expected = {}

    old.each_pair do |key, value|
      value.each do |x|
        expected[x.downcase.to_s] = key
      end
    end
    expected
  end
end

class ETL
  def self.transform data
    data.each.inject({}) do |new_data, (score, letters)|
      letters.each.inject(new_data) { |data, l| data[l.downcase] = score; data }
    end
  end
end

class ETL
  def self.transform(old)
    @@new = {}
    old.each do |j, k| #separate hash
      k.each do |item| #iterate through array
        @@new[item.downcase] = j
      end
    end
    @@new
  end
end

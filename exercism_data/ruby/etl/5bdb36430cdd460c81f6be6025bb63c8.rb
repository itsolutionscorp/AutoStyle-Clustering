class ETL
  def self.transform(old)
    hsh = {}
    old.each do |k,v|
      hsh.merge!(Hash[v.map!(&:downcase).zip(Array.new(v.size, k))])
    end
    hsh
  end
end

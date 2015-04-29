class ETL
  def self.transform(input)
    new_system = {}
    input.each_pair do |k, arr|
      arr.each {|v| new_system.store v.downcase, k}
    end
    new_system
  end
end

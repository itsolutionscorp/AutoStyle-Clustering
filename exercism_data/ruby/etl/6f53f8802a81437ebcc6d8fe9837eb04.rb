class ETL
  def initialize(old_hash)
    @old_hash = old_hash
  end

  def self.transform(old_hash)
    new(old_hash).build_new_hash
  end

  def build_new_hash
    new_hash = {}

    @old_hash.each_pair do |key, vals| 
      vals.each {|val| new_hash[val.downcase] = key}
    end

    new_hash
  end
end

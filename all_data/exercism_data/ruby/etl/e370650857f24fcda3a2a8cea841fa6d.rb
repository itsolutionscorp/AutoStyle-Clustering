class ETL

  def self.transform(hash)
    create_new_hash(hash)
  end

  private

  def self.create_new_hash(hash)
    new_hash = Hash.new()

    hash.keys.map do |key|
      hash[key].map {|element| new_hash[element.downcase] = key }
    end

    new_hash
  end

end

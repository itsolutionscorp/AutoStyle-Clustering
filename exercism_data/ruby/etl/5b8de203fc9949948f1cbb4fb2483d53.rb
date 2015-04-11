class ETL

  def self.transform(hash)
    create_new_hash(hash)
  end

  class << self
    private

    def create_new_hash(hash)
      new_hash = Hash.new()

      hash.keys.map do |key|
        hash[key].map {|element| new_hash[element.downcase] = key }
      end

      new_hash
    end

  end
end

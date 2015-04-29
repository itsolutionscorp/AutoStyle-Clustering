class ETL

  def self.transform(old)
    new_scrabble_system(old)
  end

  class << self
    private

    def new_scrabble_system(old)
      new_system = Hash.new()

      old.keys.map do |key|
        old[key].map {|element| new_system[element.downcase] = key }
      end

      new_system
    end

  end
end

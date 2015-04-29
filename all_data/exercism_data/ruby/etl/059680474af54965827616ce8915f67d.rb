class ETL

  def self.transform(old)
    new_score(old)
  end

  class << self
    private

    def new_score(old)
      new = Hash.new()

      old.keys.map do |key|
        old[key].map {|element| new[element.downcase] = key }
      end

      new
    end

  end
end

# ever notice every one of these is a weird single-use class?
# sigh.
class Raindrops 
  class << self

    def convert(num)
      words_for(num) || n.to_s
    end

    private

    def words_for(num)
      agg = ""
      agg += "Pling" if (num % 3).zero?
      agg += "Plang" if (num % 5).zero?
      agg += "Plong" if (num % 7).zero?

      agg.empty? ? agg : nil
    end
  end
end

# ever notice every one of these is a weird single-use class?
# sigh.
class Raindrops 
  class << self

    def convert(n)
      if w = words(n) and w.length > 0
        w
      else
        n.to_s
      end
    end

    private

    def words(n)
      [pling(n), plang(n), plong(n)].compact.join('')
    end

    def pling(num)
      "Pling" if (num % 3).zero?
    end

    def plang(num)
      "Plang" if (num % 5).zero?
    end

    def plong(num)
      "Plong" if (num % 7).zero?
    end

  end
end

class Raindrops
  class << self

    def convert(n)
      drops = [3, 5, 7].select{ |p| is_factor?(n, p) }.map do |p|
        case p
        when 3 then "Pling"
        when 5 then "Plang"
        when 7 then "Plong"
        end
      end.join

      return n.to_s if drops.empty?
      drops
    end

    def is_factor?(n, prime)
      n.remainder(prime).zero?
    end

  end
end

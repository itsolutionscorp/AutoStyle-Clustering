class Raindrops
  def self.convert n
    gibber = [n % 3, n % 5, n % 7].collect.with_index do |rem, i|
      if rem == 0
        case i
          when 0 then 'Pling'
          when 1 then 'Plang'
          when 2 then 'Plong'
        end
      end
    end

    return n.to_s if gibber.compact.empty?
    gibber.join
  end
end

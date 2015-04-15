class Raindrops
  def self.convert(num)
    drop_daddy = []
    if num % 3 == 0
      drop_daddy << "Pling"
    end
    if num % 5 == 0
      drop_daddy << "Plang"
    end
    if num % 7 == 0
      drop_daddy << "Plong"
    end
    drop_daddy.empty? ? num.to_s : drop_daddy.join
  end
end

class Hamming
  def compute(strand_1, strand_2)
    x = strand_1.scan(/./)
    y = strand_2.scan(/./)
    value = 0
    puts "x = #{x}"
    puts "y = #{y}"

    if x.length > y.length
      pair_length = [x.length, y.length].min - 1
      puts "pair length #{pair_length}"
      new_strand_x = x.slice(0..pair_length)
      puts "new strand x = #{new_strand_x}"
      puts "strand y = #{y}"
      comparison_hash = new_strand_x.zip y
      puts "comparison hash = #{comparison_hash}"

        comparison_hash.each do |x, y|
          if x != y
              value = value + 1
          end
        end

        puts "counter value = #{value}"
        return value

    elsif y.length >= x.length
      pair_length = [y.length, x.length].min - 1
      puts "pair length #{pair_length}"
      new_strand_y = y.slice(0..pair_length)
      puts "new strand y = #{new_strand_y}"
      puts "strand x = #{x}"
      comparison_hash = new_strand_y.zip x
      puts "comparison hash = #{comparison_hash}"

        comparison_hash.each do |x, y|
          if x != y
            value = value + 1
          end
        end

        puts "counter value = #{value}"
        return value

    end
  end
end

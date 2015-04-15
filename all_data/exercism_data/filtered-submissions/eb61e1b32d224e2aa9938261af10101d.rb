def compute(strand_1, strand_2)
    x = strand_1.scan(/./)
    y = strand_2.scan(/./)
    value = 0
    puts "x =
    puts "y =

    if x.length > y.length
      pair_length = [x.length, y.length].min - 1
      puts "pair length
      new_strand_x = x.slice(0..pair_length)
      puts "new strand x =
      puts "strand y =
      comparison_hash = new_strand_x.zip y
      puts "comparison hash =

        comparison_hash.each do |x, y|
          if x != y
              value = value + 1
          end
        end

        puts "counter value =
        return value

    elsif y.length >= x.length
      pair_length = [y.length, x.length].min - 1
      puts "pair length
      new_strand_y = y.slice(0..pair_length)
      puts "new strand y =
      puts "strand x =
      comparison_hash = new_strand_y.zip x
      puts "comparison hash =

        comparison_hash.each do |x, y|
          if x != y
            value = value + 1
          end
        end

        puts "counter value =
        return value

    end
  end
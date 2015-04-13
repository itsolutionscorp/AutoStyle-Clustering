def compute primary_strand, secondary_strand
    distance = 0
    number_of_characters_to_compare = [primary_strand.length, secondary_strand.length].min

    number_of_characters_to_compare.times do |index|
      distance += 1 if  primary_strand[index] != secondary_strand[index]
    end

    distance
  end
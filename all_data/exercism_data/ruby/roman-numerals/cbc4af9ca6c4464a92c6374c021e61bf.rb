class Fixnum
  def to_roman
    convert
  end
  
  def convert
    to_roman_string
  end
  
  def to_roman_string
    roman_string = ''
    self.times { roman_string << 'I' }
    counting_pebbles(roman_string)
  end
  
  def counting_pebbles(roman_string)
    tablet = salamis_tablet
    tablet[1][:pos_pebbles] = roman_string.length
    move_pebbles_up_tablet(tablet)
    move_pebbles_down_tablet(tablet)
    # puts "I am the number: #{self}"
    compose_string(tablet)
  end
  
  def move_pebbles_down_tablet(tablet)
    roman_moveable_increments = [ [1,5,10],
                                  [10,50,100],
                                  [100,500,1000] ]
                                  
    roman_moveable_increments.each_with_index do |range|
      
      if tablet[range[0]][:pos_pebbles] == 4
        if tablet[range[1]][:pos_pebbles] == 0
          tablet[range[1]][:neg_pebbles] = 1
          tablet[range[1]][:pos_pebbles] += 1
        else
          tablet[range[1]][:pos_pebbles] = 0
          tablet[range[2]][:neg_pebbles] = 1
          tablet[range[2]][:pos_pebbles] += 1
        end
        tablet[range[0]][:pos_pebbles] = 0
      end
    end
    # puts tablet
  end
  
  def compose_string(tablet)
    roman_increments_reverse = [1000,500,100,50,10,5,1]
    roman_string = ''
    roman_increments_reverse.each do |incr|
      tablet[incr][:neg_pebbles].times do
        roman_string << deci_to_roman_hash[roman_base_subtraction[incr]]
      end
      tablet[incr][:pos_pebbles].times do
        roman_string << deci_to_roman_hash[incr]
      end
    end
    # puts roman_string
    roman_string
  end
  
  def move_pebbles_up_tablet(tablet)
    roman_increments = [1,5,10,50,100,500,1000]
    
    roman_increments.each_with_index do |incr,i|
      if tablet[incr][:pos_pebbles] > 0 && incr != 1000
        if tablet[incr][:pos_pebbles]*incr/roman_increments[i+1] > 0
          tablet[roman_increments[i+1]][:pos_pebbles] = 
                tablet[incr][:pos_pebbles]*incr/roman_increments[i+1]
          tablet[incr][:pos_pebbles] = 
                (tablet[incr][:pos_pebbles]*incr - 
                tablet[roman_increments[i+1]][:pos_pebbles] *
                roman_increments[i+1])/incr
        end
      end
    end
    tablet
  end
  
  def salamis_tablet      
    { 1    => {:neg_pebbles => 0, :pos_pebbles => 0},
      5    => {:neg_pebbles => 0, :pos_pebbles => 0},
      10   => {:neg_pebbles => 0, :pos_pebbles => 0},
      50   => {:neg_pebbles => 0, :pos_pebbles => 0},
      100  => {:neg_pebbles => 0, :pos_pebbles => 0},
      500  => {:neg_pebbles => 0, :pos_pebbles => 0},
      1000 => {:neg_pebbles => 0, :pos_pebbles => 0} }
  end
  
  def deci_to_roman_hash
    { 1    => 'I',
      5    => 'V',
      10   => 'X',
      50   => 'L',
      100  => 'C',
      500  => 'D',
      1000 => 'M' }
  end
  
  def roman_base_subtraction
    { 5    => 1,
      10   => 1,
      50   => 10,
      100  => 10,
      500  => 100,
      1000 => 100 }
    
  end
end

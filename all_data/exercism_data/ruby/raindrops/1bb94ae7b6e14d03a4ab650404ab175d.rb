class Raindrops
  
  def self.convert(num)
    self.new.droppify(num)
  end
  
  def droppify(num)
    translation(num)
  end
  
  private
  
  def translation(num)
    @drop_sounds ||= drop_sounds(num)
    @translation ||= 
        if @drop_sounds.empty?
          num.to_s
        else
          @drop_sounds
        end
  end

  def drop_sounds(num)
    drops = { 'Pling' => 3, 'Plang' => 5, 'Plong' => 7 }
    drops.each_with_object('') do |(drop_sound, divisor), translation|
      if num % divisor == 0
        translation << drop_sound
      end
    end
  end
end

module Year

  def self.leap? y
    if not div_by_4 y 
      false
    else 
      if not div_by_100 y 
        true
      else 
        if not div_by_400 y 
          false
        else true
        end
      end
    end
  end

  private

  def self.div_by_4 y
    y % 4 == 0
  end

  def self.div_by_100 y
    y % 100 == 0
  end

  def self.div_by_400 y
    y % 400 == 0
  end


end

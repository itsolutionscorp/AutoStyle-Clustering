class Array
  def keep
    objects_to_keep = Array.new
    self.each do |this_object|
      if yield this_object
        objects_to_keep << this_object
      end
    end
    objects_to_keep
  end

  def discard
    objects_to_discard = Array.new
    self.each do |this_object|
      unless yield this_object
        objects_to_discard << this_object
      end
    end
    objects_to_discard
  end
end

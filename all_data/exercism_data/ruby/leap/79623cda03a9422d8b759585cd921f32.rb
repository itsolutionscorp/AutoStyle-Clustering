class Year
    def Year.leap?(anio)
        if (anio % 4 == 0 and anio % 100 !=0 ) or anio % 400 == 0
            return true
        else
            return false
        end
    end
end

package org.api.mtgstock.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HandyList
{
		private Integer id;
		private String name;
		
		@Override
		public String toString() {
			return getName();
		}
	
}
